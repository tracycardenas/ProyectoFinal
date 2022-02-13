from http.client import HTTPResponse
from urllib import response
from urllib.request import DataHandler
from django.shortcuts import render
from django import template
from django.http import HttpResponse

from django.template.loader import get_template
from grpc import services
from django.core.serializers import serialize
from itsdangerous import json
from django.views.decorators.csrf import csrf_exempt
from rest_framework.decorators import api_view
from AppSistemaInstitucion.services import informesMatriculas
import requests
import urllib.request, json
import json

def Inicio (request):

   return render(request,"InicioS.html")
   
def Plantilla (request):

   return render(request,"plantilla.xhtml")

def Listar_Matriculas (request):

   return render(request,"Listar_Matriculas.xhtml")

@csrf_exempt
@api_view(['GET','POST'])
def matriculasJson(request):
      url = "http://localhost:8080/SistemaInstitucion/rs/matriculas"
      response=urllib.request.urlopen(url)
      listaM=json.loads(response.read())
      data = json.dumps(listaM)
     
      return HttpResponse(data,'application/json')

def is_ajax(self):
    return self.META.get("HTTP_X_REQUESTED_WITH") == "XMLHttpRequest"


