import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'numberFormat'
})
export class NumberFormatPipe implements PipeTransform {

  transform(value: string): string {
    let st = value.replace('.', ',');
    let pos = st.indexOf(','), cnt = 0, emp = '';
    if(pos == -1) pos = st.length;
    for(let i = st.length-1; i >= pos; i--) {
      emp += st[i];
    }
    for(let i = pos-1; i >= 0; i--) {
      ++cnt;
      emp += st[i];
      if(cnt%3 == 0 && i != 0) {
        emp += '.';
      }
    }
    let ans = '';
    for(let i = emp.length-1; i >= 0; i--) ans += emp[i];
    return ans;
  }

}
