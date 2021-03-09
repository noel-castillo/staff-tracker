import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { Day } from '../models/day';

@Injectable({
  providedIn: 'root'
})
export class DayService {

  baseURL: string = "http://localhost:8080/api";
  httpOptions = { headers: new HttpHeaders({ "Content-Type": "application/json" }) };


  constructor(private http: HttpClient) { }

  addDay(toAdd: Day): Observable<Day> {
    return this.http.post<Day>(this.baseURL + "/days/add-day", toAdd, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  getDay(id: number): Observable<Day> {
    return this.http.get<Day>(this.baseURL + "/day/" + id)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  getDays(): Observable<Day[]> {
    return this.http.get<Day[]>(this.baseURL + "/days")
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          let empty: Day[] = [];
          return of(empty);
        })
      );
  }

  getDaysByRange(startDate: Date, endDate: Date): Observable<Day[]> {
    return this.http.get<Day[]>(this.baseURL + "/days/" + startDate + "/" + endDate)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          let empty: Day[] = [];
          return of(empty);
        })
      );
  }

  editDay(day: Day, id: number): Observable<Day> {
    return this.http.put<Day>(this.baseURL + "/days/" + id, day, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  deleteDay(id: number): Observable<Day> {
    return this.http.delete<Day>(this.baseURL + "/days/delete/" + id, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }
}
