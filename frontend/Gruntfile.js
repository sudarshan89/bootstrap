// Generated on 2014-06-30 using generator-angular 0.9.1
'use strict';

// # Globbing
// for performance reasons we're only matching one level down:
// 'test/spec/{,*/}*.js'
// use this if you want to recursively match all subfolders:
// 'test/spec/**/*.js'

module.exports = function (grunt) {

    // Load grunt tasks automatically
    require('load-grunt-tasks')(grunt);

    // Time how long tasks take. Can help when optimizing build times
    require('time-grunt')(grunt);

    // Configurable paths for the application
    var appConfig = {
        app: require('./bower.json').appPath || 'app',
        dist: 'dist',
        webappDist : '../backend/src/main/webapp'
    };

    // Define the configuration for all the tasks
    grunt.initConfig({

        // Project settings
        yeoman: appConfig,

        // Watches files for changes and runs tasks based on the changed files
        watch: {
            bower: {
                files: ['bower.json'],
                tasks: ['newer:copy:bower','newer:copy:adminhtml','wiredep']
            },
            js: {
                files: ['<%= yeoman.app %>/scripts/**/*.js'],
                tasks :['newer:copy:js']
            },
            styles: {
                files: ['<%= yeoman.app %>/styles/{,*/}*.css'],
                tasks: ['newer:copy:styles', 'autoprefixer']
            },
            html :{
                files: ['<%= yeoman.app %>/views/**/*.html'],
                tasks: ['newer:copy:html']
            }
        },

        // Empties folders to start fresh
        clean: {
            // Empty all the files in the backend webapp directory
            dev:{
                options:{
                   force:true
                },
                files: [{
                    dot: true,
                    src: [
                        '.tmp',
                        '<%= yeoman.webappDist %>/*',
                        '!<%= yeoman.webappDist %>/WEB-INF/**'
                    ]
                }]
            },
            //Empty all the files present in dist directory
            dist: {
                files: [{
                    dot: true,
                    src: [
                        '.tmp',
                        '<%= yeoman.dist %>/{,*/}*',
                        '!<%= yeoman.dist %>/.git*'
                    ]
                }]
            }
        },

        // Add vendor prefixed styles
        autoprefixer: {
            options: {
                browsers: ['last 1 version']
            },
            dist: {
                files: [{
                    expand: true,
                    cwd: '.tmp/styles/',
                    src: '{,*/}*.css',
                    dest: '<%= yeoman.webappDist %>/styles/'
                }]
            }
        },

        // Automatically inject Bower components into the app
        wiredep: {
            options: {
                cwd: '<%= yeoman.app %>'
            },
            app: {
                src: ['<%= yeoman.app %>/views/admin.html'],
                ignorePath:  /..\//
            }
        },

        // Renames files for browser caching purposes
        /*filerev: {
            dist: {
                src: [
                    '<%= yeoman.dist %>/scripts/{,*//*}*.js',
                    '<%= yeoman.dist %>/styles/{,*//*}*.css',
                    '<%= yeoman.dist %>/images/{,*//*}*.{png,jpg,jpeg,gif,webp,svg}',
                    '<%= yeoman.dist %>/styles/fonts*//*'
                ]
            }
        },*/

        // Reads HTML for usemin blocks to enable smart builds that automatically
        // concat, minify and revision files. Creates configurations in memory so
        // additional tasks can operate on them
        useminPrepare: {
            html: '<%= yeoman.app %>/admin.html',
            options: {
                dest: '<%= yeoman.dist %>',
                flow: {
                    html: {
                        steps: {
                            js: ['concat', 'uglifyjs'],
                            css: ['cssmin']
                        },
                        post: {}
                    }
                }
            }
        },

        // Performs rewrites based on filerev and the useminPrepare configuration
        usemin: {
            html: ['<%= yeoman.dist %>/{,*/}*.html'],
            css: ['<%= yeoman.dist %>/styles/{,*/}*.css'],
            options: {
                assetsDirs: ['<%= yeoman.dist %>','<%= yeoman.dist %>/images']
            }
        },

        // The following *-min tasks will produce minified files in the dist folder
        // By default, your `index.html`'s <!-- Usemin block --> will take care of
        // minification. These next options are pre-configured if you do not wish
        // to use the Usemin blocks.
        // cssmin: {
        //   dist: {
        //     files: {
        //       '<%= yeoman.dist %>/styles/main.css': [
        //         '.tmp/styles/{,*/}*.css'
        //       ]
        //     }
        //   }
        // },
        // uglify: {
        //   dist: {
        //     files: {
        //       '<%= yeoman.dist %>/scripts/scripts.js': [
        //         '<%= yeoman.dist %>/scripts/scripts.js'
        //       ]
        //     }
        //   }
        // },
        // concat: {
        //   dist: {}
        // },

        imagemin: {
            dist: {
                files: [{
                    expand: true,
                    cwd: '<%= yeoman.app %>/images',
                    src: '{,*/}*.{png,jpg,jpeg,gif}',
                    dest: '<%= yeoman.dist %>/images'
                }]
            }
        },

        svgmin: {
            dist: {
                files: [{
                    expand: true,
                    cwd: '<%= yeoman.app %>/images',
                    src: '{,*/}*.svg',
                    dest: '<%= yeoman.dist %>/images'
                }]
            }
        },

        htmlmin: {
            dist: {
                options: {
                    collapseWhitespace: true,
                    conservativeCollapse: true,
                    collapseBooleanAttributes: true,
                    removeCommentsFromCDATA: true,
                    removeOptionalTags: true
                },
                files: [{
                    expand: true,
                    cwd: '<%= yeoman.dist %>',
                    src: ['*.html', 'views/**/*.html'],
                    dest: '<%= yeoman.dist %>'
                }]
            }
        },

        // ngmin tries to make the code safe for minification automatically by
        // using the Angular long form for dependency injection. It doesn't work on
        // things like resolve or inject so those have to be done manually.
        ngmin: {
            dist: {
                files: [{
                    expand: true,
                    cwd: '.tmp/concat/scripts',
                    src: '*.js',
                    dest: '.tmp/concat/scripts'
                }]
            }
        },


        // Copies remaining files to places other tasks can use
        copy: {
            dev:{
                files:[{
                    expand:true,
                    dot:true,
                    cwd: '<%= yeoman.app %>',
                    dest:'<%= yeoman.webappDist %>',
                    src:[
                        '**','!styles/*.css'
                    ]
                },{
                    expand:true,
                    dot:true,
                    dest:'<%= yeoman.webappDist %>',
                    src: ['bower_components/**/*.*']
                }]
            },
            prod:{
               files:[{
                   expand:true,
                   dot:true,
                   dest:'<%= yeoman.webappDist %>',
                   src:['<%= yeoman.dist %>/**/*.*']
               }]
            },
            bower:{
              files:[{
                  expand:true,
                  dot:true,
                  dest:'<%= yeoman.webappDist %>',
                  src:['bower_components/**/*.*']
              }]
            },
            adminhtml:{
                files:[{
                    expand:true,
                    dot:true,
                    cwd:'<%= yeoman.app %>',
                    dest:'<%= yeoman.webappDist %>',
                    src:['admin.html']
                }]
            },
            js:{
                files:[{
                    expand:true,
                    dot:true,
                    cwd:'<%= yeoman.app %>',
                    dest:'<%= yeoman.webappDist %>',
                    src:['scripts/**/*.*']
                }]
            },
            html:{
                files:[{
                    expand:true,
                    dot:true,
                    cwd:'<%= yeoman.app %>',
                    dest:'<%= yeoman.webappDist %>',
                    src:['views/**/*.*']
                }]
            },
            dist: {
                files: [{
                    expand: true,
                    dot: true,
                    cwd: '<%= yeoman.app %>',
                    dest: '<%= yeoman.dist %>',
                    src: [
                        '*.{ico,png,txt}',
                        '.htaccess',
                        '*.html',
                        'views/{,*/}*.html',
                        'images/{,*/}*.{webp}',
                        'fonts/*'
                    ]
                }, {
                    expand: true,
                    cwd: '.tmp/images',
                    dest: '<%= yeoman.dist %>/images',
                    src: ['generated/*']
                }, {
                    expand: true,
                    cwd: 'bower_components/bootstrap/dist',
                    src: 'fonts/*',
                    dest: '<%= yeoman.dist %>'
                }]
            },
            styles: {
                expand: true,
                cwd: '<%= yeoman.app %>/styles',
                dest: '.tmp/styles/',
                src: '{,*/}*.css'
            }
        },

        // Run some tasks in parallel to speed up the build process
        concurrent: {
            server: [
                'copy:styles'
            ],
            test: [
                'copy:styles'
            ],
            dist: [
                'copy:styles',
                'imagemin',
                'svgmin'
            ]
        }


    });

    grunt.registerTask('play','',function(target){
        if(target === 'dev'){
          grunt.task.run([
              'clean:dev',
              'copy:dev',
              'copy:styles',
              'autoprefixer',
              'watch'
          ]);
        }else if(target === 'prod'){
            grunt.task.run([
                'clean:dist',
                'clean:dev',
                'copy:prod'
            ]);
        }
    });

    grunt.registerTask('serve', 'Compile then start a connect web server', function (target) {
        if (target === 'dist') {
            return grunt.task.run(['build', 'connect:dist:keepalive']);
        }

        grunt.task.run([
            'clean:server',
            'wiredep',
            'concurrent:server',
            'autoprefixer',
            'connect:livereload',
            'watch'
        ]);
    });

    grunt.registerTask('server', 'DEPRECATED TASK. Use the "serve" task instead', function (target) {
        grunt.log.warn('The `server` task has been deprecated. Use `grunt serve` to start a server.');
        grunt.task.run(['serve:' + target]);
    });

    grunt.registerTask('test', [
        'clean:server',
        'concurrent:test',
        'autoprefixer',
        'connect:test',
        'karma'
    ]);



    grunt.registerTask('build', [
        'clean:dist',
        'wiredep',
        'useminPrepare',
        'concurrent:dist',
        'autoprefixer',
        'concat',
        'ngmin',
        'copy:dist',
        'cdnify',
        'cssmin',
        'uglify',
        'filerev',
        'usemin',
        'htmlmin'
    ]);

    grunt.registerTask('default', [
        'newer:jshint',
        'test',
        'build'
    ]);
};
