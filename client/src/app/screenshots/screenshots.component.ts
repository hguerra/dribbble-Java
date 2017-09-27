import {Component, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Screenshot} from './screenshot/screenshot.model';
import {ScreenshotService} from './screenshot.service';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'db-screenshots',
  templateUrl: './screenshots.component.html'
})
export class ScreenshotsComponent implements OnInit {

  screenshots: Observable<Screenshot[]>;

  constructor(private screenshotService: ScreenshotService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    // const page = this.route.parent.snapshot.params['page'];
    const page = 1;
    this.screenshots = this.screenshotService.popular(page);
  }
}
