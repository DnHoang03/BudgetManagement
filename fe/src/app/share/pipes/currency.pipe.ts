import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'currency'
})
export class CurrencyPipe implements PipeTransform {

  transform(amount:number, from:string, to:string): number {
    from = from.substring(0, 3);
    let total:number = amount;
    if(from == 'USD') {
      total = amount;
    } else if(from == 'EUR') {
      total = Math.round(total*1.083);
    } else if(from == 'JPY') {
      total = Math.round(total*0.00622);
    } else {
      total = Math.round(total*0.00003934);
    }
    to = to.substring(0, 3);
    if(to == 'USD') {
      return total;
    } else if(to == 'EUR') {
      return Math.round(total*0.9233);
    } else if(to == 'JPY') {
      return Math.round(total*160.89);
    } else {
      return Math.round(total*25419.4204);
    }
  }

}
