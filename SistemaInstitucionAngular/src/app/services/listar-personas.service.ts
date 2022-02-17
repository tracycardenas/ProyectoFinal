import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ListarPersonasService {

  
  constructor(private http:HttpClient) {
  
  }

  getUsuarios(){
    let url = "http://env-0833664.sp.skdrive.net/faces/rs/listarPersonas"
    return this.http.get<any>(url);
  }
}
