import { Component, OnInit } from '@angular/core';
import { Position } from 'src/app/models/position';
import { PositionService } from 'src/app/services/position.service';

@Component({
  selector: 'app-positions',
  templateUrl: './positions.component.html',
  styleUrls: ['./positions.component.css']
})
export class PositionsComponent implements OnInit {

  positions : Position[];
  newPosition: Position = new Position();
  positionToEdit: Position;
  showAddPositionForm: boolean = false;
  showEditPositionForm: boolean = false;

  constructor(private posService : PositionService) { }

  ngOnInit(): void {
    this.posService.getAllPositions().subscribe(list => {
      this.positions = list
    });
  }

  addPosition(): void {
    this.newPosition.enabled = true;
    console.log(this.newPosition);
    this.showAddPositionForm = false;
    this.posService.addPosition(this.newPosition).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.newPosition = new Position();
        this.reload();
      },
      (didntWork) => {
        console.error('Position Component addPosition() DID NOT WORK');
        this.reload();
      }
    );
  }

  showEdit(Position: Position){
    this.showEditPositionForm = true;
    this.positionToEdit = Position;
  }

  editPosition(): void {
    this.showEditPositionForm = false;
    this.posService.editPosition(this.positionToEdit, this.positionToEdit.id).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.reload();
      },
      (didntWork) => {
        console.error('Position Component editPosition(Position) DID NOT WORK');
        this.reload();
      }
    );

  }

  deletePosition(id: number): void {
    this.posService.deletePosition(id).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.reload();
      },
      (didntWork) => {
        console.error('Position Component deletePosition(id) DID NOT WORK');
        this.reload();
      }
    );

  }

  reload(): void {
    this.posService.getAllPositions().subscribe(list => {
      this.positions = list
    });
  }

}
