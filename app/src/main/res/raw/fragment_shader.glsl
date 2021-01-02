precision highp float;

uniform highp sampler2D u_TextureUnit;
varying highp vec2 v_Texture;

void main()
{
    gl_FragColor = texture2D(u_TextureUnit, v_Texture);
}