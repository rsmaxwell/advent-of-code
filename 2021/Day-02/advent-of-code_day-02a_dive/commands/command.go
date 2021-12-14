package commands

import (
	"fmt"
	"strconv"
	"strings"

	"github.com/rsmaxwell/advent-of-code/2021/Day-02/advent-of-code_day-02a_dive/location"
	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

type Factory interface {
	Make(value int) Command
}

type Command interface {
	Move(location location.Location) location.Location
}

var (
	lookup map[string]Factory
)

func init() {
	lookup = map[string]Factory{}
	lookup["down"] = DownFactory{}
	lookup["forward"] = ForwardFactory{}
	lookup["up"] = UpFactory{}
}

func ReadCommands(filename string) ([]Command, error) {

	lines, err := tools.ReadLines(filename)
	if err != nil {
		return nil, err
	}

	var commands []Command
	for _, line := range lines {

		fields := strings.Fields(line)

		if len(fields) != 2 {
			message := fmt.Sprintf("Could not parse '%s' as a Command", line)
			fmt.Println(message)
			return nil, fmt.Errorf(message)
		}

		name := fields[0]
		arg := fields[1]
		value, err := strconv.Atoi(arg)
		if err != nil {
			fmt.Printf("Could not parse ")
			return nil, err
		}

		factory := lookup[name]
		if factory == nil {
			message := fmt.Sprintf("Unexpected factory name: %s", name)
			fmt.Println(message)
			return nil, fmt.Errorf(message)
		}

		command := factory.Make(value)
		commands = append(commands, command)
	}

	return commands, nil
}
