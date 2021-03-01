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
}
