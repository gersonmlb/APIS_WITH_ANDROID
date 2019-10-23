from flask import Flask, jsonify,request
from flask_restful import Resource, Api
import pymysql
from sqlalchemy import create_engine
from json import dumps

app = Flask(__name__)
db = pymysql.connect("localhost","root","","bd_almacen")
cursor = db.cursor()
@app.route('/addUser/', methods=['POST'])
def index():
    nombres = request.json['nombres']
    apellidos = request.json['apellidos']
    usuario = request.json['usuario']
    clave = request.json['clave']
    estado = request.json['estado']
    cursor.execute("INSERT INTO usuario(nombres,apellidos,usuario,clave,estado) values (%s,%s,%s,%s,%s)", (nombres,apellidos,usuario,clave,estado))
    return 'Se ha añadido un nuevo usuario'

@app.route('/editUser/<int:id_usuario>', methods=['PUT'])
def editUser(id_usuario):
    nombres = request.json['nombres']
    apellidos = request.json['apellidos']
    usuario = request.json['usuario']
    clave = request.json['clave']
    cursor.execute("UPDATE usuario set nombres=%s,apellidos=%s,usuario=%s,clave=%s WHERE idusuario=%s" , (nombres,apellidos,usuario,clave,id_usuario))
    return 'Se ha modificado el usuario'

@app.route('/getAll/', methods=['GET'])
def getAll():
    cursor.execute("SELECT * FROM usuario;")
    rows = cursor.fetchall()
    resp = jsonify(rows)
    return resp

@app.route('/getById/<int:id_usuario>', methods=['GET'])
def get(id_usuario):
    cursor.execute("select * from usuario where usuario.idusuario =%s",id_usuario)
    rows = cursor.fetchone()
    resp = jsonify(rows)
    return resp

@app.route('/deleteUser/<int:id_user>', methods=['DELETE'])
def delete(id_user):
    cursor.execute("delete from usuario where idusuario=%s", (id_user))
    return 'se elimino correctamente'

if __name__ == '__main__':
    app.run(debug=True)
