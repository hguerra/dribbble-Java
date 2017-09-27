import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Screenshot} from '../screenshot/screenshot.model';
import {ScreenshotService} from '../screenshot.service';


@Component({
  selector: 'db-favorites-recent',
  templateUrl: './favorites-recent.component.html'
})
export class FavoritesRecentComponent implements OnInit {

  screenshots: Observable<Screenshot[]>;

  constructor(private screenshotService: ScreenshotService) {
  }

  ngOnInit() {
    this.screenshots = this.screenshotService.favoritesRecentlyAdded();
  }
}
