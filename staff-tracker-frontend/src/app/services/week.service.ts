import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { Week } from '../models/week';

@Injectable({
  providedIn: 'root'
})
export class WeekService {
  baseURL: string = "http://localhost:8080/api";
  httpOptions = { headers: new HttpHeaders({ "Content-Type": "application/json" }) };


  constructor(private http: HttpClient) { }

  addWeek(toAdd: Week): Observable<Week> {
    return this.http.post<Week>(this.baseURL + "/weeks/add-Week", toAdd, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  getWeek(id: number): Observable<Week> {
    return this.http.get<Week>(this.baseURL + "/week/" + id)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  getWeeks(): Observable<Week[]> {
    return this.http.get<Week[]>(this.baseURL + "/weeks")
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          let empty: Week[] = [];
          return of(empty);
        })
      );
  }

  editWeek(week: Week, id: number): Observable<Week> {
    return this.http.put<Week>(this.baseURL + "/week/" + id, week, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  deleteWeek(id: number): Observable<Week> {
    return this.http.delete<Week>(this.baseURL + "/weeks/delete/" + id, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }
}
