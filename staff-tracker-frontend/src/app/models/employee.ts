export class Employee {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    phone: string;
    address: string;
    enabled: boolean;

    constructor(
        id?: number,
        firstName?: string,
        lastName?: string,
        email?: string,
        phone?: string,
        address?: string,
        enabled?: boolean
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.enabled = enabled;
    }
}
