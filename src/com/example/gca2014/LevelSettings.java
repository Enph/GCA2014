package com.example.astrobridge;

public class LevelSettings {
	double bridgespeed;
	int flyblockspawnrate;
	int asteriodpercent;
	int length;
	
	public LevelSettings(String planet){
		if(planet=="pluto"){
			bridgespeed=1;
			flyblockspawnrate = 10;
			int asteriodpercent = 0;
			int length=50;
		}
		if(planet=="neptune"){
			bridgespeed=1.1;
			flyblockspawnrate = 9;
			int asteriodpercent = 5;
			int length=150;
		}
		if(planet=="uranus"){
			bridgespeed=1.2;
			flyblockspawnrate = 8;
			int asteriodpercent = 8;
			int length=200;
		}
		if(planet=="saturn"){
			bridgespeed=1.3;
			flyblockspawnrate = 8;
			int asteriodpercent = 12;
			int length=300;
		}
		if(planet=="jupiter"){
			bridgespeed=1.3;
			flyblockspawnrate = 8;
			int asteriodpercent = 14;
			int length=400;
		}
		if(planet=="mars"){
			bridgespeed=1.5;
			flyblockspawnrate = 7;
			int asteriodpercent = 20;
			int length=150;
		}
		if(planet=="earth"){
			bridgespeed=1.5;
			flyblockspawnrate = 8;
			int asteriodpercent = 22;
			int length=200;
		}
		if(planet=="venus"){
			bridgespeed=1.6;
			flyblockspawnrate = 7;
			int asteriodpercent =30;
			int length=150;
		}
		if(planet=="mercury"){
			bridgespeed=1.8;
			flyblockspawnrate = 6;
			int asteriodpercent = 30;
			int length=200;
		}
		if(planet=="sun"){
			bridgespeed=2;
			flyblockspawnrate = 6;
			int asteriodpercent = 20;
			int length=1000;
		}
	}
}
