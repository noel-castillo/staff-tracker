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
  addPosition : boolean = false;

  constructor(private posService : PositionService) { }

  ngOnInit(): void {
    this.posService.getAllPositions().subscribe(list => {
      this.positions = list
    });
  }

}
