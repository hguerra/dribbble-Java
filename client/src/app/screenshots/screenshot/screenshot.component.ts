import {Component, Input, OnInit} from '@angular/core';
import {Screenshot} from './screenshot.model';

@Component({
  selector: 'db-screenshot',
  templateUrl: './screenshot.component.html'
})
export class ScreenshotComponent implements OnInit {
  @Input() screenshot: Screenshot;

  constructor() {}

  ngOnInit() {
  }

}
