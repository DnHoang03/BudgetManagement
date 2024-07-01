export interface Transaction {
    id:number,
    amount:number,
    createdAt:Date,
    transferAccountId:number,
    receiveAccountId:number
};