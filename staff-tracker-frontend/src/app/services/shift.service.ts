import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { Shift } from '../models/shift';

@Injectable({
  providedIn: 'root'
})
export class ShiftService {
  
  baseURL: string = "http://localhost:8080/api";
  httpOptions = { headers: new HttpHeaders({ "Content-Type": "application/json" }) };


  constructor(private http: HttpClient) { }

  addShift(toAdd: Shift): Observable<Shift> {
    return this.http.post<Shift>(this.baseURL + "/shifts/add-Shift", toAdd, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  getShift(id: number): Observable<Shift> {
    return this.http.get<Shift>(this.baseURL + "/shift/" + id)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  getShifts(): Observable<Shift[]> {
    return this.http.get<Shift[]>(this.baseURL + "/shifts")
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          let empty: Shift[] = [];
          return of(empty);
        })
      );
  }

  editShift(shift: Shift, id: number): Observable<Shift> {
    return this.http.put<Shift>(this.baseURL + "/shifts/" + id, shift, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  deleteShift(id: number): Observable<Shift> {
    return this.http.delete<Shift>(this.baseURL + "/shifts/delete/" + id, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }
}
