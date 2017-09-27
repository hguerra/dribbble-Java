import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';


import {API_URL} from '../app.api';

import {ErrorHandler} from '../app.error-handler';
import {Screenshot} from './screenshot/screenshot.model';

@Injectable()
export class ScreenshotService {
  constructor(private http: Http) {
  }

  popular(page: number): Observable<Screenshot[]> {
    return this.http.get(`${API_URL}/shots/${page}`)
      .map(response => response.json())
      .catch(ErrorHandler.handleError);
  }

  favorites(): Observable<Screenshot[]> {
    return this.http.get(`${API_URL}/favorites`)
      .map(response => response.json())
      .catch(ErrorHandler.handleError);
  }

  favoritesByDate(): Observable<Screenshot[]> {
    return this.http.get(`${API_URL}/favorites/date`)
      .map(response => response.json())
      .catch(ErrorHandler.handleError);
  }

  favoritesRecentlyAdded(): Observable<Screenshot[]> {
    return this.http.get(`${API_URL}/favorites/recent`)
      .map(response => response.json())
      .catch(ErrorHandler.handleError);
  }
}
