
import { Position } from 'src/app/models/position';
export class Day {
    id: number;
    date: Date;
    weekId: number;
    positions: Position[];
    expand: boolean = false;

    constructor(
        id?: number,
        date?: Date,
        weekId?: number,
        positions?: Position[],
        expand?: boolean
    ) {
        this.id = id;
        this.date = date;
        this.weekId = weekId;
        this.positions = positions;
        this.expand = expand;
    }
}
