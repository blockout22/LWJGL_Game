#version 400

uniform sampler2D texture_diffuse;

out vec4 fragColor;

in vec2 pass_Texcoords;

void main()
{
	fragColor = texture(texture_diffuse, pass_Texcoords);
}