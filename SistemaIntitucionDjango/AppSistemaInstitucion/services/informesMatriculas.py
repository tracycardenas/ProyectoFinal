from urllib import request
from django.http import JsonResponse
from itsdangerous import json
import requests
import json
class listaMatriculas():

    def obtenerMatriculas():
        url = "http://localhost:8080/SistemaInstitucion/rs/matriculas"
        r=requests.get(url)
        matri={"costoHora","costoMatricula","estado","id","totalHoras"}
        x=json.loads(r)
        print(x)
        return r.json()
       