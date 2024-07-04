export interface Transaction {
    id?:number,
    name:string,
    type:string,
    amount:number,
    createdAt:Date,
    iconNoteId?:number,
    iconNoteName?:string,
    transferAccountId?:number,
    receiveAccountId?:number,
    transferAccountName?:string,
    receiveAccountName?:string
};