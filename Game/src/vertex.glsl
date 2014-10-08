#version 400

in vec3 position;
in vec2 tex_Coords;

out vec2 pass_Texcoords;

void main()
{
	gl_Position = vec4(position, 1.0);
	pass_Texcoords = tex_Coords;
}