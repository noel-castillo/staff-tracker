import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { EmployeesComponent } from './components/employees/employees.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ScheduleComponent } from './components/schedule/schedule.component';
import { PositionsComponent } from './components/positions/positions.component';
import { WeeksComponent } from './components/weeks/weeks.component';
import { DaysComponent } from './components/days/days.component';
import { ShiftsComponent } from './components/shifts/shifts.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    EmployeesComponent,
    ScheduleComponent,
    PositionsComponent,
    WeeksComponent,
    DaysComponent,
    ShiftsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
