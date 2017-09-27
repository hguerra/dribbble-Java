import {Component, OnInit} from '@angular/core';
import {Screenshot} from './screenshot/screenshot.model';
import {ScreenshotService} from './screenshot.service';
import {PaginationService} from './pagination.service';


@Component({
  selector: 'db-screenshots',
  templateUrl: './screenshots.component.html'
})
export class ScreenshotsComponent implements OnInit {

  screenshots: Screenshot[];

  constructor(private screenshotService: ScreenshotService, private paginationService: PaginationService) {
  }

  ngOnInit() {
    const actual = this.paginationService.actual;
    console.log('on init: ' + actual);
    this.screenshotService.popular(actual).subscribe(shot => this.screenshots = shot);
  }

  next(): void {
    this.paginationService.next();
    const actual = this.paginationService.actual;
    console.log('page: ' + actual);
    this.screenshotService.popular(actual).subscribe(shot => this.screenshots = shot);
  }
}
