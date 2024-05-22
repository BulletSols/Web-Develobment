export interface ICustomer {
    id: number;
    cvv?: number; // Optional property using a question mark
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    phoneNumber: string;
    address: string;
    cardNumber: string;
    expiryDate: Date;
}