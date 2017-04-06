package palyshoping.example.com.shopingforplay.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableOperator;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import palyshoping.example.com.shopingforplay.R;
import palyshoping.example.com.shopingforplay.rxjava.Course;
import palyshoping.example.com.shopingforplay.rxjava.Person;

public class RxJavaActivity extends AppCompatActivity {

    private static final String TAG = "==========>>>>>>>>>";
    private List<Person> persons;
    private Observer<String> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        observable.subscribe((Observer) subscriber);
//        observable.subscribe((Observer) action);

        final Person person = new Person();
        Course course = new Course();
        course.setActivity("体育");
        course.setChina("语文");
        course.setEnglish("英语");
        person.setCourse(course);
        person.setAge(20);
        person.setName("ly");
        String[] names = {"e", "w", "q"};
        Observable.fromArray(names)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.e(TAG, "accept: " + s);
                    }
                });
        Observable.fromArray(person)
                .subscribe(new Consumer<Person>() {
                    @Override
                    public void accept(@NonNull Person s) throws Exception {
                        Log.e(TAG, "accept: " + person.toString());
                    }
                });

        persons = new ArrayList<>();
        initPersonDataList(persons);
        Observable.fromArray(persons).subscribe(new Observer<List<Person>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Person> persons) {
                Log.e(TAG, "onNext: " + persons.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        Observable.fromArray(person).subscribe(consumer);
        Observable.fromArray(persons).subscribe(consumers);
        Observable
                .just(persons)
//                .create(new ObservableOnSubscribe<Person>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Person> e) throws Exception {
//                Person person1= (Person) e;
//                person1.setName("lllll");
//                }
//            })
                .map(new Function<List<Person>, List<String>>() {
                    @Override
                    public List<String> apply(@NonNull List<Person> person) throws Exception {
                        List<String> strings = new ArrayList<String>();
                        for (int i = 0; i < person.size(); i++) {
                            strings.add(person.get(i).getName());
                        }
                        return strings;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(List<String> person) {
                        Log.e(TAG, "onNext:qqqqqq " + person.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }
                });

        Observable.just(person)
                .flatMap(new Function<Person, ObservableSource<Course>>() {
                    @Override
                    public ObservableSource<Course> apply(@NonNull Person person) throws Exception {
                        return Observable.fromArray(person.getCourse());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Course>() {
                    @Override
                    public void accept(@NonNull Course course) throws Exception {
                        Log.e(TAG, "accept: " + course.toString());
                    }
                });
        Observable.just(persons)
                .flatMap(new Function<List<Person>, ObservableSource<List<Course>>>() {
                    @Override
                    public ObservableSource<List<Course>> apply(@NonNull List<Person> person) throws Exception {
                        List<Course> courses=new ArrayList<Course>();
                        for (int i = 0; i < person.size(); i++){
                          courses.add(person.get(i).getCourse());
                        }

                        return Observable.fromArray(courses);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Course>>() {
                    @Override
                    public void accept(@NonNull List<Course> course) throws Exception {
                        Log.e(TAG, "accept:==== " + course.toString());
                    }
                });

        observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext:======aaaa==== "+s );
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        Observable.just(person)
                .lift(new ObservableOperator<String, Person>() {
                    @Override
                    public Observer<? super Person> apply(@NonNull final Observer<? super String> observer) throws Exception {
                        return new Observer<Person>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Person person) {
                                observer.onNext(person.getName());
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        };
                    }
                })
                .subscribe(observer);


//        Observable.just(person)
//                .compose(new ObservableTransformer<Person, String>() {
//                    @Override
//                    public ObservableSource<String> apply(@NonNull Observable<Person> upstream) {
//                        return null;
//                    }
//                })
    }

    private void initPersonDataList(List<Person> persons) {
        for (int i = 0; i < 10; i++) {
            Person person1 = new Person();
            person1.setName("name" + i);
            person1.setAge(i);
            Course course = new Course();
            course.setActivity("体育" + i);
            course.setChina("语文" + i);
            course.setEnglish("英语" + i);
            person1.setCourse(course);
            persons.add(person1);
        }
    }

    private Function<String, String> function = new Function<String, String>() {
        @Override
        public String apply(@NonNull String s) throws Exception {
            return "mans";
        }
    };

    private Consumer consumerss = new Consumer<String>() {

        @Override
        public void accept(@NonNull String s) throws Exception {

        }
    };

    private Consumer consumer = new Consumer<Person>() {
        @Override
        public void accept(@NonNull Person o) throws Exception {
            Log.e(TAG, "accept: " + o.toString());
        }
    };
    private Consumer consumers = new Consumer<List<Person>>() {
        @Override
        public void accept(@NonNull List<Person> o) throws Exception {
            Log.e(TAG, "accept: " + o.toString());
        }
    };

    private Observer subscriber = new Observer<String>() {


        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(String s) {
            Log.e(TAG, "onNext:---------------- ");
        }

        @Override
        public void onError(Throwable t) {
            Log.e(TAG, "onError: ");
        }

        @Override
        public void onComplete() {
            Log.e(TAG, "onComplete: ");
        }
    };

    private Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
            e.onNext("aaaaaaaaaaa");
            e.onNext("aaaaaaaaaaa");
            e.onNext("aaaaaaaaaaa");
        }
    });

    Action action = new Action() {

        @Override
        public void run() throws Exception {

        }
    };
}
