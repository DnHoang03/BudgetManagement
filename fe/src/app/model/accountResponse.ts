import { Account } from "./account";

export interface AccountResponse {
    asset:number,
    debt:number,
    total:number,
    accounts:Account[]
}