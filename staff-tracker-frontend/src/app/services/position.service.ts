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

  addPosition(toAdd: Position): Observable<Position> {
    return this.http.post<Position>(this.baseURL + "/positions/add-position", toAdd, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  addPositionWithDay(toAdd: Position, dayId: number): Observable<Position> {
    return this.http.post<Position>(this.baseURL + "/positions/add-position/" + dayId, toAdd, this.httpOptions)
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

  editPosition(Position: Position, id: number): Observable<Position> {
    return this.http.put<Position>(this.baseURL + "/positions/" + id, Position, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  deletePosition(id: number): Observable<Position> {
    return this.http.delete<Position>(this.baseURL + "/positions/delete/" + id, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }
}
