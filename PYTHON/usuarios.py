from flask import Flask, jsonify,request
from flask_restful import Resource, Api
import pymysql
from sqlalchemy import create_engine
from json import dumps

app = Flask(__name__)
conn = pymysql.connect("localhost","root","","bd_almacen")
cursor = conn.cursor(pymysql.cursors.DictCursor)

@app.route('/addUser/', methods=['POST'])
def index():
    nombres = request.json['Nombres']
    apellidos = request.json['Apellidos']
    usuario = request.json['Usuario']
    clave = request.json['Clave']
    cursor.execute("insert into usuario(Nombres,Apellidos,Usuario,Clave,Estado) values (%s,%s,%s,%s,%s)", (nombres,apellidos,usuario,clave,1))
    print("PYTHON: Hecho desde python")
    
@app.route('/editUser/<int:idusuario>', methods=['PUT'])
def editUser(idusuario):
    nombres = request.json['Nombres']
    apellidos = request.json['Apellidos']
    usuario = request.json['Usuario']
    clave = request.json['Clave']
    cursor.execute("UPDATE usuario set Nombres=%s,Apellidos=%s,Usuario=%s,Clave=%s WHERE idusuario=%s" , (nombres,apellidos,usuario,clave,idusuario))
    print('desde PYTHON')

@app.route('/getAll/', methods=['GET'])
def getAll():
    cursor.execute("SELECT * FROM usuario;")
    rows = cursor.fetchall()
    resp = jsonify(rows)
    print( ' desde python')
    return resp

@app.route('/getById/<int:id_usuario>', methods=['GET'])
def get(id_usuario):
    cursor.execute("select * from usuario where usuario.idusuario =%s",id_usuario)
    rows = cursor.fetchone()
    resp = jsonify(rows)
    print( ' desde python')
    return resp

@app.route('/deleteUser/<int:id>', methods=['DELETE'])
def delete(id):
    cursor.execute("delete from usuario where idusuario=%s", (id))
    print( ' desde python')

if __name__ == '__main__':
    app.run(host= '192.168.43.201', port=5000, debug=True)
