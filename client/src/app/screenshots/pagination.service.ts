export class PaginationService {
  actual = 1;

  constructor() {
  }

  next(): void {
    this.actual = this.actual + 1;
  }
}
