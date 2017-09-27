import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Screenshot} from '../screenshot/screenshot.model';
import {ScreenshotService} from '../screenshot.service';

@Component({
  selector: 'db-favorites-date',
  templateUrl: './favorites-date.component.html'
})
export class FavoritesDateComponent implements OnInit {

  screenshots: Observable<Screenshot[]>;

  constructor(private screenshotService: ScreenshotService) {
  }

  ngOnInit() {
    this.screenshots = this.screenshotService.favoritesByDate();
  }

}
