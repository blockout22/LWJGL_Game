#version 400

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;
//uniform mat4 transform;

in vec3 position;
in vec2 tex_Coords;

out vec2 pass_Texcoords;

void main()
{
	//gl_Position = vec4(position, 1.0);
	gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(position, 1.0);
	//gl_Position = transform * vec4(position, 1.0);
	pass_Texcoords = tex_Coords;
}