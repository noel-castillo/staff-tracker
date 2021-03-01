export class Position {
    id: number;
    title: string;
    enabled: boolean;

    constructor(
        id?: number,
        title?: string,
        enabled?: boolean
    ) {
        this.id = id;
        this.title = title;
        this.enabled = enabled;
    }
}
