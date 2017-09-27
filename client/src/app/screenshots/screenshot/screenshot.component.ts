import {Component, Input, OnInit} from '@angular/core';
import {Screenshot} from './screenshot.model';
import {ScreenshotService} from '../screenshot.service';

@Component({
  selector: 'db-screenshot',
  templateUrl: './screenshot.component.html'
})
export class ScreenshotComponent implements OnInit {
  @Input() screenshot: Screenshot;

  constructor(private screeshotService: ScreenshotService) {
  }

  ngOnInit() {
  }

  add(): void {
    console.log('Adding to favorites');
    this.screeshotService.addToFavorites(this.screenshot);
  }

  remove(): void {
    if (this.screenshot.application_id) {
      console.log('Remove from favorites');
      this.screeshotService.removeFromFavorites(this.screenshot.application_id);
    }
  }
}
