export class Position {
    id: number;
    title: string;
    employeeId: number;
    startTime: string;
    endTime: string;
    enabled: boolean;

    constructor(
        id?: number,
        title?: string,
        employeeId?: number,
        startTime?: string,
        endTime?: string

    ) {
        this.id = id;
        this.title = title;
        this.employeeId = employeeId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
