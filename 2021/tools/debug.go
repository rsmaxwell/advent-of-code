package tools

import (
	"fmt"
)

func Debugln(verbose bool, v ...interface{}) {
	if verbose {
		fmt.Println(v...)
	}
}

func Debugf(verbose bool, format string, v ...interface{}) {
	if verbose {
		fmt.Printf(format, v...)
	}
}
