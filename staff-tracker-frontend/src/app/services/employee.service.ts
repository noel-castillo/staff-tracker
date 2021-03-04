import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Employee } from '../models/employee';
import { Observable } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  baseURL: string = "http://localhost:8080/api";
  httpOptions = { headers: new HttpHeaders({ "Content-Type": "application/json"}) };


  constructor(private http: HttpClient) { }

  addEmployee(toAdd: Employee): Observable<Employee> {
    return this.http.post<Employee>(this.baseURL + "/employees/add-employee", toAdd, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  getEmployee(id: number): Observable<Employee> {
    return this.http.get<Employee>(this.baseURL + "/employee/" + id)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  getAllEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.baseURL + "/employees")
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          let empty: Employee[] = [];
          return of(empty);
        })
      );
  }

  editEmployee(employee: Employee, id: number): Observable<Employee> {
    return this.http.put<Employee>(this.baseURL + "/employees/" + id, employee, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  deleteEmployee(id: number): Observable<Employee> {
    return this.http.delete<Employee>(this.baseURL + "/employees/delete/" + id, this.httpOptions)
      .pipe(
        tap(x => console.log(x)),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }
}
