attribute vec4 a_Position;
uniform mat4 u_Matrix;
attribute highp vec2 a_Texture;
varying highp vec2 v_Texture;

void main(){
    gl_Position = u_Matrix * a_Position;
    v_Texture = a_Texture;
}
