import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'pagination'
})
export class PaginationPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    const start = (args.page - 1) * args.perPage;
    const newValue = Array.from(value);

    return newValue.splice(start, start + args.perPage);
  }
}
