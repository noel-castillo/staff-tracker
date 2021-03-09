import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Day } from 'src/app/models/day';
import { Week } from 'src/app/models/week';
import { DayService } from 'src/app/services/day.service';
import { PositionService } from 'src/app/services/position.service';
import { WeekService } from 'src/app/services/week.service';
import { Position } from 'src/app/models/position';

@Component({
  selector: 'app-weeks',
  templateUrl: './weeks.component.html',
  styleUrls: ['./weeks.component.css']
})
export class WeeksComponent implements OnInit {

  weeks: Week[];
  showAddWeekForm: boolean = false;
  showEditWeekForm: boolean = false;
  newWeek: Week = new Week();
  weekToEdit: Week;
  viewWeekDiv: boolean = true;
  days: Day[];

  constructor(private weekService: WeekService, private dayService: DayService, private positionService: PositionService, private router: Router) { }

  ngOnInit(): void {
    this.weekService.getWeeks().subscribe(list => {
      this.weeks = list
    });
  }

  addWeek(): void {
    console.log(this.newWeek);
    this.showAddWeekForm = false;
    this.weekService.addWeek(this.newWeek).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.newWeek = new Week();
        this.reload();
      },
      (didntWork) => {
        console.error('Week Component addWeek() DID NOT WORK');
        this.reload();
      }
    );
  }

  showEdit(Week: Week){
    this.showEditWeekForm = true;
    this.weekToEdit = Week;
  }

  editWeek(): void {
    this.showEditWeekForm = false;
    this.weekService.editWeek(this.weekToEdit, this.weekToEdit.id).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.reload();
      },
      (didntWork) => {
        console.error('Week Component editWeek(Week) DID NOT WORK');
        this.reload();
      }
    );

  }

  deleteWeek(id: number): void {
    this.weekService.deleteWeek(id).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.reload();
      },
      (didntWork) => {
        console.error('Week Component deleteWeek(id) DID NOT WORK');
        this.reload();
      }
    );

  }

  reload(): void {
    this.weekService.getWeeks().subscribe(list => {
      this.weeks = list
    });
  }

  viewWeek(week: Week): void {
    this.viewWeekDiv = false;
    this.dayService.getDaysByRange(week.startDate, week.endDate).subscribe(list => {
      this.days = list
    });
    console.log(this.days);
  }

  addPositionWithDay(position: Position, dayId: number): void {
    this.positionService.addPositionWithDay(position, dayId).subscribe(list => {
      this.reload();
    });
  }

}
