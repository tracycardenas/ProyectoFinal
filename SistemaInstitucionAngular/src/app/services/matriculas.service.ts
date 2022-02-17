import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MatriculasService {

  constructor(private http:HttpClient) {
  
   }

   getMatriculas(){
     let url = "http://env-0833664.sp.skdrive.net/faces/rs/matriculas"
     return this.http.get<any>(url);
   }
}
