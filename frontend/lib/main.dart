import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget 
{
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) 
  {
    return MaterialApp(
      title: 'Box Designer',
      theme: ThemeData(primarySwatch: Colors.brown),
      home: const BoxSelectionPage(),
    );
  }
}

// Page 1: Box Selection
class BoxSelectionPage extends StatelessWidget 
{
  const BoxSelectionPage({Key? key}) : super(key: key);

  final List<String> boxTypes = const ['Dice Box', 'Card Box', 'Box'];

  @override
  Widget build(BuildContext context) 
  {
    return Scaffold(
      backgroundColor: Colors.brown[200],
      body: SafeArea(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            const SizedBox(height: 20),
            const Center(
              child: Text(
                'Choose your design',
                style: TextStyle(fontSize: 32, fontWeight: FontWeight.bold),
              ),
            ),
            const SizedBox(height: 10),
            const Center(
              child: Text(
                'Which box fits your needs',
                style: TextStyle(fontSize: 20),
              ),
            ),
            const SizedBox(height: 40),
            Expanded(
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: List.generate(3, (index) 
                {
                  return Column(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      ElevatedButton(
                        style: ElevatedButton.styleFrom(
                          fixedSize: const Size(100, 100),
                          backgroundColor: Colors.white,
                        ),
                        onPressed: () 
                        {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) =>
                                  BoxConfigPage(boxType: boxTypes[index]),
                            ),
                          );
                        },
                        child: const Icon(Icons.check_box_outline_blank, size: 50),
                      ),
                      const SizedBox(height: 10),
                      Text(
                        boxTypes[index],
                        style: const TextStyle(fontSize: 16),
                      )
                    ],
                  );
                }),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

// Page 2: Box Configuration & SVG Preview
class BoxConfigPage extends StatefulWidget {
  final String boxType;
  const BoxConfigPage({Key? key, required this.boxType}) : super(key: key);

  @override
  _BoxConfigPageState createState() => _BoxConfigPageState();
}

class _BoxConfigPageState extends State<BoxConfigPage> 
{
  final TextEditingController connectorsController = TextEditingController();
  final TextEditingController xController = TextEditingController();
  final TextEditingController yController = TextEditingController();
  final TextEditingController zController = TextEditingController();

  String svgData = '';
  bool showDownload = false;

  @override
  Widget build(BuildContext context) 
  {
    return Scaffold(
      appBar: AppBar(title: Text(widget.boxType)),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(16),
          child: Column(
            children: [
              // Connectors input
              Row(
                children: [
                  const Expanded(child: Text('Connectors per mm:')),
                  SizedBox(
                    width: 80,
                    child: TextField(
                      controller: connectorsController,
                      keyboardType: TextInputType.number,
                      decoration: const InputDecoration(border: OutlineInputBorder()),
                    ),
                  ),
                ],
              ),
              const SizedBox(height: 20),
              // Dimensions input
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  Column(
                    children: [
                      const Text('X'),
                      SizedBox(
                        width: 60,
                        child: TextField(
                          controller: xController,
                          keyboardType: TextInputType.number,
                          decoration: const InputDecoration(border: OutlineInputBorder()),
                        ),
                      ),
                    ],
                  ),
                  Column(
                    children: [
                      const Text('Y'),
                      SizedBox(
                        width: 60,
                        child: TextField(
                          controller: yController,
                          keyboardType: TextInputType.number,
                          decoration: const InputDecoration(border: OutlineInputBorder()),
                        ),
                      ),
                    ],
                  ),
                  Column(
                    children: [
                      const Text('Z'),
                      SizedBox(
                        width: 60,
                        child: TextField(
                          controller: zController,
                          keyboardType: TextInputType.number,
                          decoration: const InputDecoration(border: OutlineInputBorder()),
                        ),
                      ),
                    ],
                  ),
                ],
              ),
              const SizedBox(height: 20),
              // Fullscreen button
              Align(
                alignment: Alignment.topLeft,
                child: ElevatedButton(
                  onPressed: svgData.isNotEmpty
                      ? () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (_) => FullscreenSVG(svgData: svgData),
                            ),
                          );
                        }
                      : null,
                  child: const Text('Fullscreen'),
                ),
              ),
              const SizedBox(height: 10),
              // SVG preview
              Container(
                height: 200,
                width: double.infinity,
                color: Colors.grey[200],
                child: svgData.isNotEmpty
                    ? SvgPicture.string(svgData)
                    : const Center(child: Text('SVG preview will appear here')),
              ),
              const SizedBox(height: 20),
              // Generate & Download buttons
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  ElevatedButton(
                    onPressed: () async {
                      String generatedSvg = await generateSvg();
                      setState(() {
                        svgData = generatedSvg;
                        showDownload = true;
                      });
                    },
                    child: const Text('Generate'),
                  ),
                  if (showDownload)
                    ElevatedButton(
                      onPressed: () {
                        downloadSvg(svgData);
                      },
                      child: const Text('Download'),
                    ),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }

  // Placeholder to call backend API
  Future<String> generateSvg() async {
    // Replace with actual API call
    // Send connectorsController.text, xController.text, yController.text, zController.text
    // Return SVG string
    return '''
<svg width="100" height="100" xmlns="http://www.w3.org/2000/svg">
  <rect x="10" y="10" width="80" height="80" fill="none" stroke="black"/>
</svg>
''';
  }

  // Placeholder: download SVG
  void downloadSvg(String svg) {
    // Implement file saving logic
    print('Downloading SVG...');
  }
}

// Fullscreen page
class FullscreenSVG extends StatelessWidget {
  final String svgData;
  const FullscreenSVG({Key? key, required this.svgData}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Fullscreen SVG')),
      body: Center(
        child: SvgPicture.string(svgData),
      ),
    );
  }
}
