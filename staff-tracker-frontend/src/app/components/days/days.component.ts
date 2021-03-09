import { Component, OnInit } from '@angular/core';
import { Day } from 'src/app/models/day';
import { Router } from '@angular/router';
import { DayService } from 'src/app/services/day.service';

@Component({
  selector: 'app-days',
  templateUrl: './days.component.html',
  styleUrls: ['./days.component.css']
})
export class DaysComponent implements OnInit {

  days: Day[];
  showAddDayForm: boolean = false;
  showAddPositionForm: boolean = false;
  showEditDayForm: boolean = false;
  newDay: Day = new Day();
  dayToEdit: Day;
  dayId: number;

  constructor(private dayService: DayService, private router: Router) { }

  ngOnInit(): void {
    this.dayService.getDays().subscribe(list => {
      this.days = list
    });
  }

  addDay(): void {
    console.log(this.newDay);
    this.showAddDayForm = false;
    this.dayService.addDay(this.newDay).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.newDay = new Day();
        this.reload();
      },
      (didntWork) => {
        console.error('Day Component addDay() DID NOT WORK');
        this.reload();
      }
    );
  }

  showEdit(day: Day) {
    this.showEditDayForm = true;
    this.dayToEdit = day;
  }

  editDay(): void {
    this.showEditDayForm = false;
    this.dayService.editDay(this.dayToEdit, this.dayToEdit.id).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.reload();
      },
      (didntWork) => {
        console.error('Day Component editDay(day) DID NOT WORK');
        this.reload();
      }
    );

  }

  deleteDay(id: number): void {
    this.dayService.deleteDay(id).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.reload();
      },
      (didntWork) => {
        console.error('Day Component deleteDay(id) DID NOT WORK');
        this.reload();
      }
    );

  }

  reload(): void {
    this.dayService.getDays().subscribe(list => {
      this.days = list
    });
  }

  addPosition(): void {

  }

}
