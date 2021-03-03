import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Position } from '../models/position';
import { Observable } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PositionService {

  baseURL: string = "http://localhost:8080/api";
  httpOptions = { headers: new HttpHeaders({ "Content-Type": "application/json" }) }


  constructor(private http: HttpClient) { }

  addEmployee(toAdd: Position): Observable<Position> {
    return this.http.post<Position>(this.baseURL + "/add-position", toAdd, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  getPosition(id: number): Observable<Position> {
    return this.http.get<Position>(this.baseURL + "/position/" + id)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  getAllPositions(): Observable<Position[]> {
    return this.http.get<Position[]>(this.baseURL + "/positions")
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          let empty: Position[] = [];
          return of(empty);
        })
      );
  }

  editPosition(toEdit: Position): Observable<Position> {
    return this.http.put<Position>(this.baseURL + "/positions/" + toEdit.id, toEdit, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  deletePosition(toDelete: Position): Observable<Position> {
    return this.http.delete<Position>(this.baseURL + "/positions/delete/" + toDelete.id, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }
}
