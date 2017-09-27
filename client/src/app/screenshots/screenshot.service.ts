import {Injectable} from '@angular/core';
import {Headers, Http} from '@angular/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';


import {API_URL} from '../app.api';

import {ErrorHandler} from '../app.error-handler';
import {Screenshot} from './screenshot/screenshot.model';


@Injectable()
export class ScreenshotService {

  constructor(private http: Http, private router: Router) {
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

  addToFavorites(screenshot: Screenshot): void {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    this.http.post(`${API_URL}/favorites`, screenshot, {headers: headers})
      .subscribe(res => {
        console.log('Adding to favorites: ', res.json());
      });
    this.router.navigateByUrl('/screenshots');
  }

  removeFromFavorites(id: number): void {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    this.http.delete(`${API_URL}/favorites/${id}`, {headers: headers})
      .subscribe(res => {
        console.log('Removing from favorites: ', res);
      });
    this.router.navigateByUrl('/screenshots');
  }
}
