import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LibroDiarioService {

    
  constructor(private http:HttpClient) {
  
  }

  getLibros(){
    let url = "http://env-0833664.sp.skdrive.net/faces/rs/librosdiarios"
    return this.http.get<any>(url);
  }
}
