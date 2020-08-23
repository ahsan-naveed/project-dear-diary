package main

import (
	"context"
	"fmt"
	"log"

	"github.com/ahsan-naveed/grpc/calculator/calculatorpb"
	"google.golang.org/grpc"
)

func main() {
	fmt.Println("Calculator Client")
	cc, err := grpc.Dial("localhost:50051", grpc.WithInsecure())
	if err != nil {
		log.Fatalf("Couldn't connect: %v", err)
	}
	defer cc.Close()
	c := calculatorpb.NewCalculatorServiceClient(cc)
	doUnary(c)
}

func doUnary(c calculatorpb.CalculatorServiceClient) {
	fmt.Println("Starting to do a Unary RPC...")
	req := &calculatorpb.CalculatorRequest{
		X:  100,
		Y:  10,
		Op: "/",
	}
	res, err := c.Calculator(context.Background(), req)
	if err != nil {
		log.Fatalf("Failed to get CalculatorResponse: %v", err)
	}
	log.Printf("Response from Calculator: %v", res)
}
