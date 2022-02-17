import { Estudiante } from "./estudiante";

export class Matricula{
    id?: number;
    estado?: false;
    costoMatricula?: number;
    costoHora?: number;
    totalHoras?: number;
    estudiante: Estudiante = new Estudiante;
    
}