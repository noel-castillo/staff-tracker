export class Day {
    id: number;
    date: Date;
    weekId: number;

    constructor(
        id?: number,
        date?: Date,
        weekId?: number
    ) {
        this.id = id;
        this.date = date;
        this.weekId = weekId;
    }
}
