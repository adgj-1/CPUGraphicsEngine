# Graphics Engine Project Base
The purpose of this repo is to act as a foundation for basic 3D rendered game\
This computes rendered frames completely using CPU based algorithms instead of OpenGL, and draws\
by pixel manipulation using Java AWT.

# Getting Started
Modify init and update function in Engine class\
init is run once on program start, update can be used for recurrent behaviour\
A demo is provided, previewing 3 objects
- .obj model loaded from file system, shading material as algorithm computed from Z depth
- Cube with shading material loaded with texture from BufferedImage
- Cube with shading material as indexed color palettes

Both cubes have overlay enabled for edge and vertex label

![alt text](./resources/Screenshot%202023-05-18%20154537.png)

# Camera Overlay
This class can be applied to Camera to add visual alterations besides rendered objects, it can be extended to create custom overlays
## overlayFace
Draws overlay for each rendered face
## overlayVertex
Draws overlay for each rendered vertex (This is drawn before fragment shader, so it is very likely to be covered up)
## overlayUI
Draws overlay once without any other parameters, suitable for creating 2D GUI

# Material
Custom fragment shader that can be applied to an object\
Each face requires a Material to be rendered\
## getColor
Can contain the algorithm that computes the color of the fragment given its position
- mapping - UV mapping coordinate
- v1, v2, v3 - vertex 3d coordinates of the face currently computing
- pos - 2d coordinate of fragment currently computing
- pointZ - Z value of the fragment currently computing
# _3DObject
Base class for objects existing in 3D space\
All 3D objects extends from this
