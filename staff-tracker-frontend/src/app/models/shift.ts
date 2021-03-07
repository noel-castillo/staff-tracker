export class Shift {

    id: number;
    name: string;
    startTime: number;
    endTime: number;

    constructor(
        id?: number,
        name?: string,
        startTime?: number,
        endTime?: number
    ) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
